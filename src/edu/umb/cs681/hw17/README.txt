Online Subject Enrollment App: 
A multi-threaded subject enrollment application in which students try to enroll for subjects. 
There is a requirement for some subject. For enrolment in subject 681 student needs to be 
enrolled in subject 682 and for enrolment in subject 682 student needs to be enrolled in subject 681. 
Students can enroll for those subjects in any order.

Thread-Safe Issues:
Now 2 students try to enroll for subjects. Now student1 is able to enroll for subject 681 
and tries to enroll for subject 682 while student2 has enrolled for Subject 682 and now is trying 
to enroll for subject 681. Both these students requests are in a multi-threaded environment and 
happen at the same time and this creates a deadlock.

Thread-Safe revisions:
The app can be made thread safe by implementing global lock ordering using hashcode. The locks will be aquired in
the same way at all places. Initially the lock on subject 681 will happen and then lock for subject 682
will happen. This order will make the app deadlock-safe.