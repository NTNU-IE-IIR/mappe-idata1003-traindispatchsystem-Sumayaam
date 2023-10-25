package edu.ntnu.stud;

import java.time.LocalTime;

/** Represents a class that contain information about Train Departures.
 *
 *
 * @author Sumaya
 * @version 2023-10-20
 */

public class TrainDeparture {
        private final LocalTime departureTime;
        private final String line;
        private final String trainNumber;
        private final String destination;
        private final LocalTime delay;
        private final int track;

        /** Creates a train departure Object with specified attributes.
         *
         * @param departureTime  The departure time of the train(has to be valid LocalTime).
         * @param line           The train line.(cannot be null or empty line).
         * @param trainNumber    The train number(uniqye for each day and must be positive).
         * @param destination    The train destination.
         * @param delay          The delay of the train (hh.mm) (00.00, if no delay).
         * @param track          the track number of the train(empty line if no track is assigned).
         */
        public TrainDeparture(LocalTime departureTime, String line, String trainNumber,
                              String destination, LocalTime delay, int track) {

            this.departureTime = departureTime;
            this.line = line;
            this.trainNumber = trainNumber;
            this.destination = destination;
            this.delay = LocalTime.of(0, 0);
            this.track = track;
        }


        /**
         * Returns the departure time.
         *
         * @return departure time.
         *
         */
        public LocalTime getDepartureTime() {

            return departureTime;
        }

        /**
         * Returns the line of the train.
         *
         * @return line of the train.
         */
        public String getLine() {
            return line;
        }

        /**
         * Returns the train number of the train.
         *
         * @return train number of the train.
         */
        public String getTrainNumber() {
            return trainNumber;
        }

        /**
         * Returns the train destination.
         *
         * @return destination of the train.
         */
        public String getDestination() {

            return destination;
        }

        /**
         * Returns the delay of the train
         *
         * @return delay of the train.
         */
        public LocalTime getDelay() {
            return delay;
        }

        /**
         * Returns the Track of the train
         *
         * @return track of the train.
         */

        public int  getTrack() {
            return track;
        }





    }


