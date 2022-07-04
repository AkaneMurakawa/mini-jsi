# mini-jsi
Mini Java script interpreter

- support basic arithmetic calculation and decimal calculation.
- support arithmetic priority and parentheses parsing.

## Basic structure of simple interpreter
```text
Lexical ———Token———> Parser ———AST———> Interpreter
```

## Example
```shell script
JSI 0.0.1 Java script interpreter Windows 10 amd64
>> 1*(2+3)-(4/2)
1*(2+3)-(4/2)
[debug] token: 
Token{literalType='number', literal='1'}
Token{literalType='symbols', literal='*'}
Token{literalType='terminator', literal='('}
Token{literalType='number', literal='2'}
Token{literalType='symbols', literal='+'}
Token{literalType='number', literal='3'}
Token{literalType='terminator', literal=')'}
Token{literalType='symbols', literal='-'}
Token{literalType='terminator', literal='('}
Token{literalType='number', literal='4'}
Token{literalType='symbols', literal='/'}
Token{literalType='number', literal='2'}
Token{literalType='terminator', literal=')'}
[debug] ast: 
Binary{left=Binary{left=1, operator=*, right=Binary{left=2, operator=+, right=3}}, operator=-, right=Binary{left=4, operator=/, right=2}}
3.0
```

## Reference
- [编译原理(哈工大)](https://www.bilibili.com/video/BV1zW411t7YE?share_source=copy_web)
