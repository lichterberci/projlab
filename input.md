Create Room                              object created r1
Create Room                              object created r2
Create Room                              object created r3
Create Room                              object created r4
Create Room                              object created r5
Create Room                              object created r6
Create Room                              object created r7
Create Room                              object created r8
Create Door                              object created d1
create Door                              object created d2
create Door                              object created d3
create Door                              object created d4
create Door                              object created d5
create Door                              object created d6
create Door                              object created d7
create Door                              object created d8
create Door                              object created d9
create Door                              object created d10
create Teacher                           object created t1
create Student                           object created s1
create Towel                             object created tw1
create CSE                               object created cse1
create SlideRule                         object created sr1
SetRooms d1 r1 r2                        
SetRooms d2 r8 r7
SetRooms d3 r7 r2
SetRooms d4 r2 r6
SetRooms d5 r5 r6
SetRooms d6 r2 r5
SetRooms d7 r2 r3
SetRooms d8 r3 r4
SetRooms d9 r4 r5
SetRooms d10 r4 r6
AddActor r1 t1
AddActor r7 s1
AddItem r2 tw1
AddItem r3 sr1
PickUp cse1 s1
Activate cse1
UseDoor t1 d1
PickUp t1 tw1                            
TimePassed s1                            object created dp1
UseDoor s1 d3
DropOutAll t1
UseDoor s1 d7
PickUp sr1 s1                            (student won)
