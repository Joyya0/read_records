      * *****************************************************************
      * Program name:    MYPROG
      * Original author: Anna Olson
      *
      * Maintenance Log
      * Date      Author        Maintenance Requirement
      * --------- ------------ ---------------------------------------
      * 06/15/26 Anna Olson    Created for COBOL learning
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
           SELECT INPUT-FILE ASSIGN TO "records.txt"
           ORGANIZATION IS LINE SEQUENTIAL.

      * *****************************************************************

       DATA DIVISION.

       FILE SECTION.
       FD INPUT-FILE
           RECORDING MODE IS F
           RECORD CONTAINS 80 CHARACTERS 
           BLOCK CONTAINS 0 RECORDS 
           LABEL RECORDS ARE STANDARD 
           DATA RECORD IS STUDENT-REC.

       01 STUDENT-REC.
               05 STUDENT-ID   PIC 9(04).
               05 NAME         PIC 9(20).
               05 COURSE       PIC X(07).
               05 CREDITS      PIC 9(01).
      
       01 LINE-DATA PIC X(32).
       

       
       WORKING-STORAGE SECTION. 

       01 WS-COUNT PIC 9(5) VALUE 0.
       01 WS-FINISHED PIC X VALUE "N".

      * *****************************************************************

       PROCEDURE DIVISION.
       
       MAIN.
           OPEN INPUT INPUT-FILE 

           PERFORM UNTIL WS-FINISHED = "Y"
              ADD 1 TO WS-COUNT
              READ INPUT-FILE
                 
                 AT END 
                    MOVE "Y" TO WS-FINISHED
               
                 NOT AT END 
                    DISPLAY "NAME: " NAME "| COURSE ENROLLED " COURSE
                    " " CREDITS " CREDITS"
              END-READ
           END-PERFORM

           CLOSE INPUT-FILE
           STOP RUN.

      * *****************************************************************
