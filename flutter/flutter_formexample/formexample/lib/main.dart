import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => new _MyAppState();
}


//class MyApp extends StatelessWidget {
class _MyAppState extends State<MyApp> {
  // This widget is the root of your application.
  TextEditingController nameController = TextEditingController();
  int _radioValue = 0;

  void _handleRadioValueChange(int value) {
    setState(() {
      _radioValue = value;
      switch (_radioValue) {
        case 0:
          Fluttertoast.showToast(msg: 'Correct !',toastLength: Toast.LENGTH_SHORT);
          break;
        case 1:
          Fluttertoast.showToast(msg: 'Try again !',toastLength: Toast.LENGTH_SHORT);
          break;
        case 2:
          Fluttertoast.showToast(msg: 'Try again !',toastLength: Toast.LENGTH_SHORT);
          break;
      }
    });
  }


  @override
  Widget build(BuildContext context) {
    Widget RowOne = Container(
        padding: const EdgeInsets.all(8),
        child: Row(children: [
          Text('Label: '),
          Text('Hello Form'),
        ]));

    Widget RowTwo = Container(
        padding: const EdgeInsets.all(8),
        child: Row(children: [
          Text('Name: '),
          new Flexible(  //needed, for the render to figure out dynamic in a row, which is static.
              child: new TextField(
                controller: nameController,  //captures the text, I think.
                decoration: const InputDecoration(helperText: "Enter Name"),
              ))
        ]));
    Widget RowThree = Container(
        padding: const EdgeInsets.all(8),
        child: Row(children: <Widget>[
          Text('Raido Buttons?'),
          new Radio(
            value: 0,
            groupValue: _radioValue,
            onChanged: _handleRadioValueChange,
          ),
          new Text('Euro'),
          new Radio(
            value: 1,
            groupValue: _radioValue,
            onChanged: _handleRadioValueChange,
          ),
          new Text('Pound'),
          new Radio(
            value: 2,
            groupValue: _radioValue,
            onChanged: _handleRadioValueChange,
          ),
          new Text('Yen'),
        ]));

    Widget RowFour = Container(
        padding: const EdgeInsets.all(8),
        child: Row(children: [
          Text('Button alert: '),
          RaisedButton(
            onPressed: () {
              if (nameController.text.isEmpty)
                Fluttertoast.showToast(msg: 'Alert!!',toastLength: Toast.LENGTH_SHORT);
              else
                Fluttertoast.showToast(msg: 'Hi ' + nameController.text,toastLength: Toast.LENGTH_SHORT);
            },
            child: const Text('Toast!'),
          )
        ]));
    Widget RowFive = Container(
        padding: const EdgeInsets.all(8),
        child: Row(children: [
          Text('Picture: '),
          Image.asset('images/phone.png', fit: BoxFit.cover)
        ]));

    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text('Flutter layout demo'),
        ),
        body: Column(
          children: [
            RowOne,
            RowTwo,
            RowThree,
            RowFour,
            RowFive,
          ],
        ),
      ),
    );
  }
}
