import 'package:flutter/material.dart';

class FirstScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('First Screen'),
      ),
      body: SafeArea(
        child: Center(
          child: Text('Welcome To Fisrt Screen'),
        ),
      ),
    );
  }
}