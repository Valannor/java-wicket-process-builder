**ProcessBuilder synopsis**

Класс _ProcessBuilder_ позволяет запускать сторонние скрипты и перехватывать результат выполнения.
Производится это путем делегирования вызова коммандной строке ОС.
Для работы требуется:
1. Передать объекту список аргументов (массив);
2. Запустить _ProcessBuilder_ методом _.start()_ (метод возвращает объект класса Process);
3. Далее, мы можем создать _InputStream_ из объекта _Process_ или получить отчет о выполнении (метод _.waitFor()_ - вернет -1 при ошибке запуска скрипта или 0 при успехе);
4. _InputStream_ из пункта 3 можно использовать для чтения результата выполнения скрипта.

Пример:
```
String[] command = ...;
List<String> result = new ArrayList<>();
ProcessBuilder processBuilder = new ProcessBuilder(command);

try {
    Process process = processBuilder.start();
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

    String s;
    while ((s = reader.readLine()) != null) {
        result.add(s);
    }

    System.out.println("Process finished with exit code " + process.waitFor());
} catch (IOException | InterruptedException e) {
    e.printStackTrace();
}
```

Важно правильно определить аргументы, которые передаем объекту _ProcessBuilder_.
В обычном случае все выглядит так:
1. Файл запуска коммандной строки ОС (cmd.exe для Windows);
2. Диск, на который установлена система и скрипт-раннер конкретного типа скрипта (входят в библиотеку того или иного языка - groovy, python, javascript);
3. Расположения скрипта, который мы хотим запустить;
4. Агрументы, которые будут нужны скрипту из пункта 3.

Пример:
```
new ProcessBuilder("cmd.exe", "/c", "...\\dir\\MyScript.groovy", "argument1", "argument2", ...);
``` 

P.S.:
1. Обратите внимание, что путь до скрипта описан двумы обратными слешами (\\), а путь до системного диска обычным слешем (/). Это связано с особенностью обработки путей системой Windows, на которой выполнялось данное задание.
2. Все аргументы, передаваемые конструктору ProcessBuilder являются строками.


**Wicket synopsis**

**_I. Интернационализация (и разделение инициализации этикетки):_**

1) В html - работает для _input_, _button_

`<input wicket:id="submit" type="submit" wicket:message="value:button_value_in_properties, title:button_title_in_properties"/>` - аргумент _wicket:message_ с параметрами _value_ и _title_ (в html);


2) В java-коде - работает для _label_.

-Полная форма:
```
    add(new Label("label_id", new AbstractReadOnlyModel<String>() {
        @Override
        public String getObject() {
            return getString("label_title_in_properties");
        }
    }));
```

-Сокращенная версия (лямбда):

```add(new Label("label_id", (IModel) () -> getString("label_title_in_properties")));```

P.S.: Для интернационализации мы в своем приложении создаем свитчер, который будет менять указатель на property-файл в зависимости от выбранного языка.



**_II. Инициализация компонента wicket:_**

Для создания любого компонента нужно указать id (по нему html и java-код будут связываться) и модель (_Model_).

1) В самом простом случае компонент задается обычной строкой `new Model<>("Model value and title")`;

2) Если изменения поступают со стороны отображения (input поле) и значение в дальнейшем заносится в определенный объект, то выгоднее создать ассоциацию между этим объектом и элементом отображения.
Для этого, вместо объекта модели создается `new PropertyModel<String>(myJavaBeanObject, "fieldOfMyObject")`.



**_III. AJAX_**

Wicket позволяет реализовать функционал ajax (все шаблонные операции в js реализуются самим фреймворком).
Для реализации функционала требуется:

1. Компоненту, подверженному изменению, выставить свойство `setOutputMarkupId(true)`;
2. Вместо обычного компонента, производящего изменения (_Button_), вызвать его аналог с приставкой Ajax (_AjaxButton_);
3. Реализовать логику изменения компонента из пункта 1 в ajax-методе компонента из пункта 2;
3. В ajax методе объекту-параметру (_AjaxRequestTarget_) добавить компонент из пункта 1/3.

Пример:
```
    labelObject.setOutputMarkupId(true);                        //1
    AjaxButton button = new AjaxButton("button_id") {           //2
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                labelObject.setVisible(false);                  //3
                target.add(labelObject);                        //4
            };
    };
```


Если мы хотим переключать свойства компонентов многократно, нам нужно также выставить свойство `setOutputMarkupPlaceholderTag` в _true_.