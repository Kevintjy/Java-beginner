To make this ATM machine program run properly， you must understand and follow the following instructions:

1. NEVER DELETE: do not delete any txt/ser/java files. It's where the data and documentations stored.
Every time you open the program, it will automatically load previous data from txt files.

2. FOLLOW THE INSTRUCTION: Always following the instructions shown on the screen to save your time.
If you enter something else, the program will not recognize, and will ask you to do it again.

3. INITIAL DATA PART: There are a java file called CreateDataForTest, after you run it, the user interface will pump out. And the database is going to have 5 users, 10 accounts, 2 managers,
2 worked ATM, 2 unworked ATM, 2 requests. The User has id and owns some accounts, and their initial password are all 1234, user with
id 1 has two accounts(credit and line credit), user with id 2 has two accounts(chequing and Saving), user with id3 has four accounts
(credit, line credit, chequing and Saving), user with id 4 has two chequing accounts and user with id 5 has no account, but he
requesting a chequing and credit account, so he sends two requests). All the accounts has 0 balance. The managers has the id and their password,
 their id are 1 and 2 and password 1234. The nonworked ATM cannot be used and the two working ATM with id 1, 2 has 50 number of five dollar,
 ten dollar, twenty dollar and fifty dollar respectively. (BTW: There is no user having the default account, please set it)


4. DEPOSIT PART: There are a deposits file created for testing deposit function. Each line whether contains 2 or 6 entries, separated
by ",". For the line with two entries(Cheque Deposits), the first number is the accounts number , and second is the
amount deposit(eg: 1, 202). For the line with six entries(Cash Deposits), the first number is the accounts number, and second is the
id of ATM which the cash is deposited in, and the third is the number of five dollars put in, the forth is # of ten dollars,
 the fifth is # of twenty dollars, and the last is # of fifty dollars(eg: 3,1,2,2,2,2). If you want to modify the
 deposit file, make sure to only use the test accounts and ATMS which has been in ATMData database, also follow the same format
 stated above.

5. ATM PART: An ATM will close its withdraw function when bills stored in reach the low limit.
Then the users will not be able to withdraw any money from this machine.
Managers should add money to ATMs ASAP when see Alerts.
When any of denomination is less than the number of twenty, the ATM becomes nonworked ATM, and it gonna write Alert.txt.

6: USER PART:
There is a folder call UserFolder storing users' information, where each file inside the folder contains different user's information.
Each user has an attribute call itAccount, which contains all of his/her account(s).
User can be created through Manager, and manager gets to decide what his/her id is, and the initial password for each user is 1234.
A user needs to select "I am user" and provide user's id with corresponding password to in order to proceed to user's main page.

If the user want to make change to any account he has, he needs to enter his user's main page and click "choose account".
    Then the program will display all account associated with him, and he needs to pick the one that he wants.
    After selecting the account, he can check account's information, withdraw, deposit, pay bill or transfer money.
If the user want to send account creation request, he needs to enter his user's main page and choose "send request".
    Then the program will display all available option he has.
    He then will need to pick one type of account that he wants to apply. The request will be sent to managers.
If the user want to make change to his user's password, he needs to enter his user's main page and select "change password".
    Then the program will double check and see whether he wants to change password.
    The new password must be all integers, and the length must be between 4 to 6 letters.
    He will need to enter the new password two times, they must match, or the password will not be changed.
If the user want to check his total balance, he needs to enter his user's main page and choose "total balance".
    Then the program will double check and see whether he wants to see the balance, and then the balance will be shown.
If the user want to change his primary account, then he needs to enter his user's main page and select "change primary account".
    If this user only has one account, he can not change the primary account.
    If this user has more than one account, he can choose the account he want and make it to be the new primary account.


7: MANAGER PART:
The default manager information will be recorded in the file named “Manager DATA”. (You can create new manager though the file)
When we run the program, the manager instance will be automatically created with name, id, password, created date,
(id name and password will be given in the file). The manager needs the default information to login to the system by selecting “I am manager”
 in the main page. Both id and passwords need to be correct in order to enter the manager first page, otherwise it will back to the login page.
 There are five operations in the manager first page.
1)	Creating new user
2)	Checking request
3)	Checking ATM
4)	Undo last transaction
5)	Change manager password
7.1	You need enter the user id and user name for the newly created account, the system will return whether the new user
is valid or not by showing “account created” or “failed to created user”. You can choose Y/N to create user again or
back to the manager first page

7.2	You can check the users’ accounts requests. If there is no request, the system will back to manager first page.
If there is some request stored, the system will show the first request on the screen. You can choose to agree or reject that request.
 With your agreement, you will need to create account by entering account number. You can try again if you enter an invalid number.
 Whether the request is rejected or approved, this request will be deleted from the request list and back to the manager first page.

7.3	You can check if there is any non-working ATM. You can choose which ATM to add.

7.4	You can undo the transaction of a specific account by entering its account number, the system will check if it is a valid account.
 Then it will need user password to authorize the undo. The last transaction will be shown on the screen.
  You can choose yes or no to undo the transaction.

7.5	You need to enter the new password twice to change the password of manager.


8: ACCOUNT PART:

8.1 The account information will be stored in the file called "AccountFolder". Each time the program is initialized, all of
current existing accounts will be loaded to the the ArrayList named "accounts" in class Accounts, which will make the
system easier to operate with accounts. The system will automatically record accounts' information back to file, including
newly created accounts, every time after the system shuts down.

8.2 Each user will have a ItAccount, which contains all accounts belonging to this user. Therefore it's easier to track a user's
accounts and calculate total balance with this implementation.

8.3 There are four types of accounts in the system, and they are subclass of Account. Check design.pdf to see the inheritance
structure, attributes, and methods of each class. The features of each type of account are  same as the handout.

8.4 Check flowchart.pdf to see available services in the system. (The saving accounts interests is update in main class,
and we use an file called config_date to record the date when should we update saving's interests.)


9: REQUESTS PART:

9.1 User can send requests of account creation and a Request object will record the user's requirement.

9.2 Managers can access these requests in the ArrayList of the class Requests and decide whether to accept a request. A
new account of required type will be created if the request is accepted. After a request has been processed, it will
be removed from the ArrayList.

9.3 The not yet processed requests will be recorded in to the file called "RequestData" after the system shuts down, and
will be loaded once the system is initialized again.


10. CONFIG_DATE: The last add interests date is 2019-03-01.

11. STAFF PART:
11.1 The default manager information will be recorded in the file named “StaffFolder”. (You can create new manager though the file)

11.2 When we run the program, the staff instance will be automatically created with name, id, password, created date,
(id name and password will be given in the file). The staff needs the default information to login to the system by selecting “I am a staff”
 in the main page. Both id and passwords need to be correct in order to enter the manager first page, otherwise it will back to the login page.

11.3 There are two operations that staff can access.
1)	Creating new user
2)	Checking request
And since a staff is also a user of the system, s/he also have all of user's functions.


