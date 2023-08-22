# book-mapper
ISBN lookup implemented using hash tables in Java. Written for CS 400 (Fall 2022) at UW Madison

## Project Description
The application allows users to search through a collection of books stored in the CSV format.
 Users can look up a book directly through its ISBN number. The application will also check
 whether the ISBN is well formed and warn the user if this is not the case. In addition, the titles of
 all books can be searched by single words or phrases that they contain. The search can be
 further refined by specifying the name or part of the name of the authors of the books to search
 for. The application is based on a hash table that maps ISBN numbers (the keys) to books
 (values). To allow searching through titles and authors, the hash table also supports iterating
 through all the books (values) in the table