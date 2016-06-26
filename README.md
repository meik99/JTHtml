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
| ,,, | h2 |
| *** | p |
| \-\-\- | div |
| +++ | code |
| $ref: "link" "text" | \<a href='link'>text\</a> |

###Sample.txt
```
### This is a header (h1) ###
,,, This is a smaller header (h2) ,,,
*** This is a paragraph (p). ***
--- This is a div. ---
---
This is a div too.
---
+++ System.out.println("This will be inside a <code> tag") +++
$ref: this.willBeA.link Link-Text
```

###Sample.txt.html
```
<html>
	<head>
		<meta charset='utf-8'/>
	</head>
	<body>
		<h1> This is a header (h1) \<h1>
		<h2> This is a samller header (h2) \<h2>
		<p> This is a paragraph (p). \</p>
		<div> This is a div. \</div>
		<div>
			This is a div too.
		</div>
		<code> System.out.println("This will be inside a \<code> tag") \</code>
		<a href='this.willBeA.link'> Link-Text \</a>
	</body>
</html>
```
##Rules and important stuff
~~1 After a key there has to be a whitespace, tab or newline.~~

2 Newlines, tabs and whitespaces will be too converted to html-equivalents.
   (~~Fix in the future~~ Not a bug actually)
   
~~3 Encapsulating tags is not possible at the moment. (No ",,, +++ +++ ,,," -> will not work)
   Although you can use the link-key inside tags.~~
   
4 This project isn't perfect but i will fix and do stuff in the near future.

