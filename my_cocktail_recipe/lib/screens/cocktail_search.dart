// ignore_for_file: prefer_const_constructors, unnecessary_const, use_key_in_widget_constructors

import 'package:flutter/material.dart';
import 'package:mycocktailrecipe/screens/cocktail_list.dart';

class CocktailSearch extends StatefulWidget {
  @override
  _CocktailSearchState createState() => _CocktailSearchState();
}

class _CocktailSearchState extends State<CocktailSearch> {
  //
  final cocktailName = TextEditingController();
  String strDrink = '';
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("CocktailsRecipe"),
        centerTitle: true,
      ),
      body: GestureDetector(
        onTap: () {
          FocusScope.of(context).unfocus();
          TextEditingController().clear();
        },
        child: ListView(
          children: [
            SizedBox(
              width: 30,
            ),
            Padding(
                padding: EdgeInsets.all(10),
                child: Container(
                  padding: EdgeInsets.all(10),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      TextField(
                        controller: cocktailName,
                        decoration: InputDecoration(
                          labelText: 'Ingrese el nombre de un cocktail',
                          labelStyle:
                              TextStyle(color: Colors.white, fontSize: 15),
                          hintStyle: TextStyle(fontSize: 20),
                          filled: true,
                          border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(20)),
                        ),
                      ),
                      ElevatedButton(
                        onPressed: () {
                          strDrink = cocktailName.text;
                          Navigator.of(context).push(
                            MaterialPageRoute<void>(
                                builder: (BuildContext context) {
                              return CocktailList(strDrink: strDrink);
                            }),
                          );
                        },
                        child: Text("Buscar"),
                      )
                    ],
                  ),
                )),
          ],
        ),
      ),
    );
  }
}
