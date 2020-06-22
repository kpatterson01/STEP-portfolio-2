// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet; 

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    /*Algorithmn - How to find a meeting given events of attendees and a meeting request?
    1. The attendes<> in Meeting Request iterate through events and find the 
      same exact attendees
    2. Once have attendee, get the TimeRange for their event and store it in meetingConflicts array
    3. if the request duration is > the whole day return an empty avability list, 
       if there are no meeting conflicts return the whole day in the availability list  
    4. else find the overlaps of the time ranges and merge them together 
        5. for meetingConflicts size() 
            6. if time ranges overlap set smaller time range and largest time range as new meetign conflict 
            7. else if it does not overlap add the time gap to availability list only if the gap duration
               is larger than meeting request 
    8. return availability list 
    
    Time Complexity: O(n^2) 
    */
    ArrayList<TimeRange> meetingConflicts = new ArrayList<TimeRange>(); 
    ArrayList<TimeRange> availability = new ArrayList<TimeRange>(); 

    //Adds meeting conflicts to meetingConficts ArrayList  
    for (Event getEvent : events) {
        for (String findAttendee : getEvent.getAttendees()) {
            if (request.getAttendees().contains(findAttendee)) {
                meetingConflicts.add(getEvent.getWhen());
            } 
        }
    }

    if (request.getDuration() > TimeRange.WHOLE_DAY.duration()) { //if request duration is larger than whole day return an empty array 
        return availability; 
    } else if (meetingConflicts.isEmpty()) { //else if meeting conflicts are empty then return the whole day 
        availability.add(TimeRange.fromStartEnd(TimeRange.START_OF_DAY, TimeRange.END_OF_DAY, true)); 
        return availability; 
    } else { //else merge the overlapping time ranges and find the gaps inbetween 
        TimeRange availabilityStart;
        if (meetingConflicts.get(0).start() != 0) { 
            availabilityStart = TimeRange.fromStartEnd(TimeRange.START_OF_DAY, meetingConflicts.get(0).start(), false); 
        } else {
            availabilityStart = TimeRange.fromStartEnd(meetingConflicts.get(0).end(), meetingConflicts.get(1).start(), false); 
        }

        //Check the availabilityStart and see if the duration is >= the request duration
        if (availabilityStart.duration() >= request.getDuration()) {
            availability.add(availabilityStart); 
        }

        //Merging Time conflicts together      
        for (int i = 1; i < meetingConflicts.size(); i++) {
            TimeRange prevConflict = meetingConflicts.get(i - 1);
            TimeRange currentConflict = meetingConflicts.get(i);

            //when it overlaps, merge the two times together
            if(currentConflict.overlaps(prevConflict)) {
                TimeRange earliestStart; 
                TimeRange laterEnd; 
                
                if (prevConflict.start() < currentConflict.start()) {
                    earliestStart = prevConflict; 
                } else {
                    earliestStart = currentConflict; 
                }

                if (prevConflict.end() > currentConflict.end()) {
                    laterEnd = prevConflict; 
                } else {
                    laterEnd = currentConflict; 
                }
                meetingConflicts.set(i, TimeRange.fromStartEnd(earliestStart.start(), laterEnd.end(), false)); 
        
            } else { //Add availability gap if the duration fits 
                if (request.getDuration() <= currentConflict.start() - prevConflict.end()) {
                    availability.add(TimeRange.fromStartEnd(prevConflict.end(), currentConflict.start(), false)); 
                } 
            }         
        }
        //Set end time if meeting request fits the duration 
        TimeRange availabilityEnd = TimeRange.fromStartEnd(meetingConflicts.get(meetingConflicts.size() - 1).end(), TimeRange.END_OF_DAY, true); 
        if (availabilityEnd.duration() >= request.getDuration() && availabilityEnd.end() != TimeRange.END_OF_DAY) {
            availability.add(availabilityEnd); 
        }

        //Deletes Duplicates and then add to a new ArrayList 
        LinkedHashSet<TimeRange> noDuplicates = new LinkedHashSet<>();
        noDuplicates.addAll(availability); 
        ArrayList<TimeRange> availabilityNoDuplicates = new ArrayList<>(noDuplicates); 
        return availabilityNoDuplicates; 
    }
          
  }
  
}

/*Things to Improve 
- Add a helper method for merging the time conflicts 
- Find the duplicates in the beginning and not the end 
    - For instance, I could've used a LinkedHashSet to store my time conflcits 
      and then iterate through the set and then return the arraylist at the end 
- Time Complexity is currently O(N^2), I can definitely improve on this by searching
through the Time Conflicts differntly 
*/