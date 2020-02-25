# Image Compression Algorithms: Aliasing and LZW

Template Code for Image class and Main class provided by instructor Dr. Elaine Kang.
Compression Algorithms provided by Saba Mahbub

Compile requirement
======================================
JDK Version 7.0 or above

Compile Instruction on Command Line:
======================================
javac java*.java

Execution Instruction on Command Line:
======================================
java CS4551_Mahbub



How Program Works:
======================================
When running the program will display a menu with options: 

1. Aliasing
2. Dictionary Coding
3. Quit

If user inputs 1 then the program will prompt the user to enter m, n, and k which are thickness, radius, and reduction size respectively. We assume k is a power of two.
Once user enters their desired numbers the program will create, display, and save four images. These images are the original 512x512 image, and the rest are 512/k x 512/k images. Each of the smaller images utilize subsampling but one has no filter attached, the second has an averaging filter attached, and the last has a weighted filter attached.

If user inputs 2 then the program will prompt the user to enter a file path to a .txt file to read. Should the input be faulty the program will ask again, looping until a valid file path is given. Once a file path is verified, program reads the file. It then encodes the file printing the initial values of the file, dictionary created as file is encoded, and finally the encoded message. The program then automatically decoded the message and prints the output which when decoded is equivalent to the original input. The console will then display the compression ratio of encoding.

If user input is anything else the program will quit.


Comparing filters:
======================================
Using the following inputs for aliasing:
M=1, N=20, K=2
M=1, N=20, K=4
M=3, N=20, K=2
M=3, N=20, K=4
M=5, N=40, K=2
M=5, N=40, K=4
I have come to the following conclusion.
The best method is filter2 which utilizes a weighted filter, then filter1 which is an averaging filter, and the worst case is subsampling without a filter. This is the case because the weighted filter acts like how the eye would see things giving higher weight to the pixels closest to the center. Averaging filter is also good filter as it blurs the pixel colors together there is a cohesion of pixels whereas no filter only grabs one pixel and can create many gaps.
