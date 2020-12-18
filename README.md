# L-Systems

Рисование L-систем с использованием черепашьей графики.

[*Класс LSystem*](https://github.com/cheshire-salmon/L-Systems/blob/main/src/LSystem.java)
реализует L-систему, где набор правил хранится в массивах right и left.
Метод [apply](https://github.com/cheshire-salmon/L-Systems/blob/main/src/LSystem.java#L51) применяет этот набор правил к строке.

[*Класс MyLSystem*](https://github.com/cheshire-salmon/L-Systems/blob/main/src/MyLSystem.java)
рисует объекты по заданным правилам. Правила задаются в файле.

См. примеры L-систем заданных в файлах [TREE.TXT](https://github.com/cheshire-salmon/L-Systems/blob/main/TREE.TXT) и [FLOWER.TXT](https://github.com/cheshire-salmon/L-Systems/blob/main/FLOWER.TXT)

1-я строка в файле - строка символов L-системы.

2-я строка в файле - первая часть правил.

3-я строка в файле - вторая часть правил.

4-я сторка в файле - сколько раз правила будут применены к строке.
