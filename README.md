# JTHtml
##Introduction

This is a interpreter that transforms text to html using some key signs.
It is written in plain java.

##Usage

The text-file is given as an argument. In the same directory as the source file
a output file is placed with the same name as the input plus ".html" as extension.

If your file is called "source.txt" the ouput will be "source.txt.html".

Start the programm with the following command
```
java -jar JTHtml.jar "source.txt"
```

###Keys
| Key | Html-Tag |
|-----|----------|
| ### | h1 |
|-----|------|
| ,,, | h2 |
|-----|----|
| *** | p |
|-----|---|
| \-\-\- | div |
|--------|-----|
| +++ | code |
|-----|------|
| $ref "link" "text" | \<a href='link'>text\</a> |
|--------------------|-------------------------|

