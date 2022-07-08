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
Token{tokenKind='number', literal='1'}
Token{tokenKind='symbols', literal='*'}
Token{tokenKind='terminator', literal='('}
Token{tokenKind='number', literal='2'}
Token{tokenKind='symbols', literal='+'}
Token{tokenKind='number', literal='3'}
Token{tokenKind='terminator', literal=')'}
Token{tokenKind='symbols', literal='-'}
Token{tokenKind='terminator', literal='('}
Token{tokenKind='number', literal='4'}
Token{tokenKind='symbols', literal='/'}
Token{tokenKind='number', literal='2'}
Token{tokenKind='terminator', literal=')'}
[debug] ast: 
Binary{left=Binary{left=1, operator=*, right=Binary{left=2, operator=+, right=3}}, operator=-, right=Binary{left=4, operator=/, right=2}}
3.0
```

## Reference
- [编译原理(哈工大)](https://www.bilibili.com/video/BV1zW411t7YE?share_source=copy_web)
