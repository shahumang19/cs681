Online Subject Enrollment App: 
A multi-threaded subject enrollment application in which students try to enroll for subjects. 
There is a requirement for some subject. For enrolment in subject A student needs to be 
enrolled in subject B and for enrolment in subject B student needs to be enrolled in subject A. 
Subject A and B has only 1 spot left for each student.

Thread-Safe Issues:
Now 2 students try to enroll for subjects. Now student1 is able to enroll for subject A 
and tries to enroll for subject B while student2 has enrolled for Subject B and now is trying 
to enroll for subject A. Both these students requests are in a multi-threaded environment and 
happen at the same time and this creates a deadlock.

Thread-Safe revisions:
The app can be made thread safe by implementing global lock ordering. The locks will be aquired in
the same way at all places. Initially the lock on subject A will happen and then lock for subject B
will happen. This order will be maintained through out the app.