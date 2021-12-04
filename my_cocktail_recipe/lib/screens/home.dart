import 'package:flutter/material.dart';
import 'package:mycocktailrecipe/screens/cocktail_search.dart';

class Home extends StatelessWidget {
  const Home({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: CocktailSearch(),
    );
  }
}
