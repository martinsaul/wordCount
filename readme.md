# WordCount

## Summary

WordCount is a simple implementation in Java for streaming files and performing a count of duplicated words.
The output is a sorted word count CSV report file on the same folder as the source file.

## Build commands

Action | Command
--- |  ---
Compile | `gradlew clean build`
Generate Java Doc | `gradlew javadoc`

## How to run

This application allows you to parse multiple files sequentially. Below you can find the commands to perform said action.

### Single file

`java -jar path_to_output_jar path_to_source_file`


### Multiple files
`java -jar path_to_output_jar path_to_source_file1 path_to_source_file2 ...`


## Expected Input and Output

### Input

test.case
```
github java 
java spring boot
```

### Output

test.case_result.csv
```
Word, Count
boot, 1
github, 1
java, 2
spring, 1
```

## JavaDoc
[Link to latest JavaDoc](https://martinsaul.github.io/wordCount/overview-summary.html)