      * *****************************************************************
      * Program name:    MYPROG
      * Original author: Anna Olson
      *
      * Maintenance Log
      * Date      Author        Maintenance Requirement
      * --------- ------------ ---------------------------------------
      * 06/15/26 Anna Olson    Created for enrollment verification
      * *****************************************************************

       IDENTIFICATION DIVISION.
       PROGRAM-ID. MYPROG.
       AUTHOR. Anna Olson.
       DATE-WRITTEN. 06/14/26.
       DATE-COMPILED. 06/14/26.

      * *****************************************************************

       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SOURCE-COMPUTER. IBM-3081.
       OBJECT-COMPUTER. IBM-3081.
      * *****************************************************************
       INPUT-OUTPUT SECTION. 
       FILE-CONTROL. 
           SELECT INPUT-FILE
           ASSIGN TO "request.txt"
           ORGANIZATION IS LINE SEQUENTIAL.

           SELECT OUTPUT-FILE
           ASSIGN TO "output.txt"
           ORGANIZATION IS LINE SEQUENTIAL.

      * *****************************************************************

       DATA DIVISION.

       FILE SECTION.
       FD INPUT-FILE
            LABEL RECORDS ARE STANDARD
            DATA RECORD IS STUDENT-REC.

       01 STUDENT-REC.
           05 NAME         PIC A(20).
           05 COURSE       PIC X(07).
           05 CREDITS      PIC 9(01).
           05 TOTALCREDITS PIC 9(02).

       FD OUTPUT-FILE
           LABEL RECORDS ARE STANDARD
           DATA RECORD IS OUTPUT-REC.


       01 OUTPUT-REC PIC X(80).
           
           
       WORKING-STORAGE SECTION.
       
       01 WS-FINISHED PIC X VALUE "N".
           88 END-OF-FILE VALUE "Y".
       
       01 UPCREDITS PIC 99 VALUE 0.

      * *****************************************************************

       PROCEDURE DIVISION.

       MAIN.
       
           OPEN INPUT INPUT-FILE
                OUTPUT OUTPUT-FILE
       
           PERFORM UNTIL END-OF-FILE
       
               READ INPUT-FILE
       
                   AT END
                       SET END-OF-FILE TO TRUE
       
                  NOT AT END

           ADD CREDITS TO TOTALCREDITS GIVING UPCREDITS
           MOVE SPACES TO OUTPUT-REC
       
           IF UPCREDITS > 20
       
               STRING
                   "ENROLLMENT OVERLOADED"
                   " | TOTAL CREDITS: "
                   UPCREDITS
                   DELIMITED BY SIZE
                   INTO OUTPUT-REC
               END-STRING
       
           ELSE
       
               STRING
                   "ENROLLMENT SUCCESS"
                   " | TOTAL CREDITS: "
                   UPCREDITS
                   DELIMITED BY SIZE
                   INTO OUTPUT-REC
               END-STRING
       
           END-IF
       
           WRITE OUTPUT-REC
       
               END-READ
       
           END-PERFORM
       
       
           CLOSE INPUT-FILE
                 OUTPUT-FILE
       
           STOP RUN.

      * *****************************************************************
