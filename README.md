**Wicket synopsis**

**_I. Интернационализация (и разделение инициализации этикетки):_**

1) В html - работает для input, button

`<input wicket:id="submit" type="submit" wicket:message="value:button_value_in_properties, title:button_title_in_properties"/>` - аргумент wicket:message с параметрами value и title (в html);


2) В java-коде - работает для label.

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

Для создания любого компонента нужно указать id (по нему html и java-код будут связываться) и модель (Model).

1) В самом простом случае компонент задается обычной строкой `new Model<>("Model value and title")`;

2) Если изменения поступают со стороны отображения (input поле) и значение в дальнейшем заносится в определенный объект, то выгоднее создать ассоциацию между этим объектом и элементом отображения.
Для этого, вместо обекта модели создается `new PropertyModel<String>(myJavaBeanObject, "fieldOfMyObject")`.



**_III. AJAX_**

Wicket позволяет реализовать функционал ajax (все шаблонные операции в js реализуются самим фреймворком).
Для реализации функционала требуется:

1. Компоненту, подверженному изменению, выставить свойство `setOutputMarkupId(true)`;
2. Вместо обычного компонента, производящего изменения (Button), вызвать его аналог с приставкой Ajax (AjaxButton);
3. Реализовать логику изменения компонента из пункта 1 в ajax-методе компонента из пункта 2;
3. В ajax методе объекту-параметру (AjaxRequestTarget) добавить компонент из пункта 1/3.

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


Если мы хотим переключать свойства компонентов многократно, нам нужно также выставить свойство `setOutputMarkupPlaceholderTag` в true.