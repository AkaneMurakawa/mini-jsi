# mini-jsi
Mini Java script interpreter

## Example
```shell script
JSI 0.0.1 Java script interpreter Windows 10 amd64
>> 1+2*3-4/1+1
1+2*3-4/1+1
[debug] token: 
Token{literalType='number', literal='1'}
Token{literalType='symbols', literal='+'}
Token{literalType='number', literal='2'}
Token{literalType='symbols', literal='*'}
Token{literalType='number', literal='3'}
Token{literalType='symbols', literal='-'}
Token{literalType='number', literal='4'}
Token{literalType='symbols', literal='/'}
Token{literalType='number', literal='1'}
Token{literalType='symbols', literal='+'}
Token{literalType='number', literal='1'}
[debug] ast: 
Binary{left=Binary{left=Binary{left=Literal{value=1}, operator=Token{literalType='symbols', literal='+'}, right=Binary{left=Literal{value=2}, operator=Token{literalType='symbols', literal='*'}, right=Literal{value=3}}}, operator=Token{literalType='symbols', literal='-'}, right=Binary{left=Literal{value=4}, operator=Token{literalType='symbols', literal='/'}, right=Literal{value=1}}}, operator=Token{literalType='symbols', literal='+'}, right=Literal{value=1}}
4.0
```
