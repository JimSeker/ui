import 'package:flutter/material.dart';

class SecondScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Second Screen'),
      ),
      body: SafeArea(
        child: Center(
          child: Text('Welcome To Second Screen'),
        ),
      ),
    );
  }
}