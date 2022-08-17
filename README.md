# trem-simulator

> Exercise proposed by the Algorithms and Programming: Linear Structures course at Unisinos 

In a country town, the only transportation between the different neighborhoods is a train. There are several trains in trains that run from point A (which is on one side of town) to point B (which is on the other side of town). Between points A and B there are N stations (one in each neighborhood), which are 20 kilometers apart from each other. 

The first trains leave at 8 am, and the last one leaves at 5:30 pm. From the starting time, every half hour on the hour a train leaves from one point and goes to the other. The number of trains is not important, as there is always a train available to leave. The speed of each train is exactly 60km/h.

The journey of each train between the two points would take the same amount of time, were it not for the stopping time at each station. 

At each station the train will stop for people to get off and on, and the stopping time depends on how many people are getting on and off. Since the doors of the train are tight, only one person can get off or on at a time. Naturally, the people getting on must wait until all the people who want to get off get off, and then get on the train (also one by one). The time it takes to get on and off the train is the same: 30 seconds.

What happens on this train line, however, is that there is only one track for both journeys (from A to B and B to A). As such, the trains will use the same space to come and go, which can lead to accidents. To avoid situations like this, the city has created deviations before and after each station, so that the trains can to deviate from each other in case they need to pass through some part of the route in the same time.

The image below illustrates the deviations created by the city:

![image](https://user-images.githubusercontent.com/63256286/185188600-2a32cf30-fa45-498f-a5ff-eebc4e2ecbf4.png)
