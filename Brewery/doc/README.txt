 for setting up the project in ECLIPSE:
 
 
 LINUX SYSTEM:
 
 The RXTX library is needed
  1.sudo apt-get install librxtx-java
  2.Add the RXTXcomm.jar downloaded (usr/share/java) to your build path.
  3.In the build path, select the JRE library and set the Native library to point
	to the .so libraries for serial communication, which should be found in
	(usr/lib/jni/)