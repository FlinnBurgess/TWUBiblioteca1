My implementation of the biblioteca app.
To run the app, you can either manually compile the java files and run the app from the BibliotecaApp.class file, or you can open the project in the IntelliJ IDE and run it from there.

Customer ID: 123-4567
Password: password


### Feedback
1. Only create the methods you need - while getters and setters are standard, only write what you know
you'll need.
2. Good use of overriding equals and compareTo.
3. Missing unit Tests for classes and methods (like getters?)
4. Good use of extending Exception for Custom Exceptions, but Movies don't deserve the same type of treatment?
5. Check classes access level, not all need to be public.
6. Unused imports and possible simplification of certain statements - IntelliJ can flag these up for you
TIP: Use Alt+Enter / Option+Enter to see suggestions.
7. Think about code reusability - most of your code in Book and Movie is identical -- is there anything you could do
to reduce the duplication of this code?
8. What feedback should I get if I attempt to checkout movies that don't exist?
9. Movies cannot be returned?