# WheresMoney
A project for mobile team building, to learn basic usage of Jetpack Compose.

This app contains:

Main Page
--
1. Toolbar with setting menu icon
2. Expense list: indicate each expense's information, click list item will launch expense editor
3. Floating button: click to create a new expense (reuse expense editor)

Expense Editor
--
0. Full screen with toolbar or a Non-full screen dialog look view
1. Editable fields: name, amount, date, category
2. Date Picker: any kind of view to pick a date (do not use 3rd-party library) with a confirm button
3. Category Picker: any kind of list to pick a category
4. Save button

Setting Page
--
1. Toolbar with back button
2. Category list: indicate each category's information, click list item will launch category editor
3. Floating button: click to create a new category (reuse category editor)

Category Editor
--
0. Full screen with toolbar or a Non-full screen dialog look view
1. Editable fields: name, color
2. Color Picker: use three sliders to seek RGB values with a preview color view and a confirm button
4. Save button

We expect to complete below missed composables to let the app work fine.
```
ExpenseListView
ExpenseView
DatePicker
CategoryPicker
SettingScreen
CategoryScreen
ColorPicker
```

# Enjoy!
