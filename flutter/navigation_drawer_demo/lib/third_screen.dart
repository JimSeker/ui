import 'package:flutter/material.dart';

class ThirdScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Third Screen'),
      ),
      body: SafeArea(
        child: Center(
          child: Text('Welcome To Third Screen'),
        ),
      ),
    );
  }
}