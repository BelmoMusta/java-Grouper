# java-Grouper
Making it easy to use grouping functions over Maps that contain collections as values.

### Example of use :  

```java 
Grouper<Integer, String> grouper = new ArrayListGrouper<>();
        grouper.put(1, "a");
        grouper.put(1, "b");
        grouper.put(1, "c");
        grouper.put(1, "c");
        grouper.put(2, "c");
```
### Output : 
```
{1=[a, b, c, c], 2=[c]}
```
