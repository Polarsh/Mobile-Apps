// ignore_for_file: camel_case_types

import 'package:flutter/material.dart';
import 'package:mycocktailrecipe/models/cocktail.dart';
import 'package:mycocktailrecipe/screens/cocktail_search.dart';
import 'package:mycocktailrecipe/utils/http_helper.dart';

class CocktailCard extends StatefulWidget {
  final String idDrink;
  const CocktailCard({Key? key, required this.idDrink}) : super(key: key);

  @override
  _CocktailCardState createState() => _CocktailCardState();
}

class _CocktailCardState extends State<CocktailCard> {
  late Future<List> cocktails;
  late HttpHelper httpHelper;

  @override
  void initState() {
    httpHelper = HttpHelper();
    cocktails = fetchCocktailById();
    super.initState();
  }

  Future<List> fetchCocktailById() {
    return httpHelper.fetchCocktailsById(widget.idDrink);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: [
          IconButton(
              onPressed: () {
                Navigator.of(context).push(
                  MaterialPageRoute<void>(builder: (BuildContext context) {
                    return CocktailSearch();
                  }),
                );
              },
              icon: const Icon(Icons.search)),
        ],
      ),
      body: FutureBuilder<List>(
        future: cocktails,
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            return ListView.builder(
                itemCount: snapshot.data?.length,
                itemBuilder: (context, index) {
                  return CocktailCard_(cocktail: snapshot.data?[index]);
                });
          } else if (snapshot.hasError) {
            return Text('${snapshot.error}');
          }
          return const Center(
            child: CircularProgressIndicator(),
          );
        },
      ),
    );
  }
}

class CocktailCard_ extends StatefulWidget {
  final Cocktail cocktail;
  const CocktailCard_({Key? key, required this.cocktail}) : super(key: key);

  @override
  _CocktailCard_State createState() => _CocktailCard_State();
}

class _CocktailCard_State extends State<CocktailCard_> {
  @override
  void initState() {
    super.initState();
  }

  @override
  void setState(fn) {
    if (mounted) {
      super.setState(fn);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        ListTile(
            title: Text(widget.cocktail.strDrink),
            subtitle: Text(widget.cocktail.strCategory),
            leading: Image.network(widget.cocktail.strDrinkThumb)),
        Text("Instrucciones:"),
        Padding(
          padding: const EdgeInsets.all(20.0),
          child: Text(widget.cocktail.strInstructions),
        ),
      ],
    );
  }
}
