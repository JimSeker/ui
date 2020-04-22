import 'package:flutter/material.dart';
import 'package:navigation_drawer_demo/drawer_widget.dart';

/// This example uses a lot of code from https://github.com/am1994/navigation_drawer
/// some changes and additions have been made as well.

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Navigation Drawer Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Home Screen'),
      ),
      drawer: DrawerWidget(),
      body: SafeArea(
        child: Center(
          child: Text('Welcome to Home Screen'),
        ),
      ),
    );
  }
}