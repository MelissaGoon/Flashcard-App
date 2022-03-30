# Flashcard Game
## CPSC 210 Project 

###Project Proposal:
- **What will the application do?**
  - This application will be used to create and study sets 
  of flashcards which consist of a question/ statement and 
  an answer. They can be studied through the use of fill in the answer
  style questions. Users will be able to study from 
  questions they previously got wrong, and their progress
  through the set (number of questions correctly answered) will be recorded.
- **Who will use it?**
  - This application is mostly targeted at university or high school
  students to aid in studying for exams. However, it can be used
  by anyone as a memorization tool.
- **Why is this project of interest to you?**
  - As a person studying Biology in addition to Computer Science
  I find flash cards the best way to memorise terminology. Being
  able to test my recall with questions helps a lot with remembering
  everything.

### User Stories
- As a user I want to be able to add a flashcard to a flashcard set.
- As a user I want to be able to see all the flashcards in a set.
- As a user I want to be able to start a new game and be prompted to fill in the answer for a statement.
- As a user I want to be able to see if I got a question right or wrong after answering it. 
- As a user I want to be able to see a final score after the game has ended.
- As a user I want to be able to remove a flashcard from a flashcard set.
- As a user I want to be able to save my flashcard set to file.
- As a user I want to be able to be able to load my flashcard set from file. 

### Phase 4: Task 2
**The first three add events occur when the flashcards are automatically loaded from file.**

Loaded flashcard set from:./data/flashcardSet.json

Sat Mar 26 14:37:41 PDT 2022
Flashcard added

Sat Mar 26 14:37:41 PDT 2022
Flashcard added

Sat Mar 26 14:37:41 PDT 2022
Flashcard added

Sat Mar 26 14:37:47 PDT 2022
Flashcard added

Sat Mar 26 14:37:53 PDT 2022
Flashcard added

Sat Mar 26 14:38:00 PDT 2022
Flashcard removed

Sat Mar 26 14:38:05 PDT 2022
Flashcard removed

Process finished with exit code 0

### Phase 4: Task 3
Due to the relative simplicity of my program I don't think any refactoring would be required. 
As far as I can tell, there do not seem to be any issues with coupling (medium coupling).
If the program had additional functionality, it may be useful to create different
classes for different functions of the GUI in order to follow the Single Responsibility Principle.