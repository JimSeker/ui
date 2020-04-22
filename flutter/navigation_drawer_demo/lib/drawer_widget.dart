import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:navigation_drawer_demo/first_screen.dart';
import 'package:navigation_drawer_demo/second_screen.dart';
import 'package:navigation_drawer_demo/third_screen.dart';

class DrawerWidget extends StatefulWidget {
  @override
  _DrawerWidgetState createState() => _DrawerWidgetState();
}

class _DrawerWidgetState extends State<DrawerWidget> {
  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        children: <Widget>[
          DrawerHeader(
            decoration: BoxDecoration(
              gradient: RadialGradient(
                colors: [ Colors.white,Colors.blueAccent],
                stops: [0.3, 1],
              ),
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Container(
                  width: 90.0,
                  height: 90.0,
                  decoration: BoxDecoration(
                      shape: BoxShape.circle,
                      image: DecorationImage(
                          fit: BoxFit.cover,
                          image: AssetImage('images/user.png')
                      )
                  ),
                ),
                Text('User',style: TextStyle(fontWeight: FontWeight.bold),)
              ],
            ),
          ),

          ListTile(
            leading: Icon(Icons.add),
            title: Text('First Screen',style: TextStyle(fontWeight: FontWeight.bold),),
            onTap: (){
              Navigator.pop(context);  //closes the drawer, now change screens.
              Navigator.push(context, MaterialPageRoute(
                  builder: (context) => FirstScreen()
              ));
            },
          ),
          ListTile(
            leading: Icon(Icons.check),
            title: Text('Second Screen',style: TextStyle(fontWeight: FontWeight.bold),),
            onTap:  (){
              Navigator.pop(context);  //closes the drawer, now change screens.
              Navigator.push(context, MaterialPageRoute(
                  builder: (context) => SecondScreen()
              ));
            },
          ),
          ListTile(
            leading: Icon(Icons.insert_emoticon),
            title:Text('Third Screen',style: TextStyle(fontWeight: FontWeight.bold),),
            onTap:  (){
              Navigator.pop(context);  //closes the drawer, now change screens.
              Navigator.push(context, MaterialPageRoute(
                  builder: (context) => ThirdScreen()
              ));
            },
          ),
          ListTile(
            leading: Icon(Icons.home),
            title:Text('Home Screen',style: TextStyle(fontWeight: FontWeight.bold),),
            onTap:  (){
              Navigator.pop(context);  //just closes the drawer.
            },
          )
        ],
      ),
    );
  }
}
