import 'package:flutter/material.dart';
import 'package:mycocktailrecipe/models/cocktail.dart';
import 'package:mycocktailrecipe/screens/cocktail_card.dart';
import 'package:mycocktailrecipe/screens/cocktail_search.dart';
import 'package:mycocktailrecipe/utils/http_helper.dart';

class CocktailList extends StatefulWidget {
  final String strDrink;
  const CocktailList({Key? key, required this.strDrink}) : super(key: key);

  @override
  _CocktailListState createState() => _CocktailListState();
}

class _CocktailListState extends State<CocktailList> {
  late Future<List> cocktails;
  late HttpHelper httpHelper;

  @override
  void initState() {
    httpHelper = HttpHelper();
    cocktails = fetchCocktailByName();
    super.initState();
  }

  Future<List> fetchCocktailByName() {
    return httpHelper.fetchCocktailsByName(widget.strDrink);
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
                  return CocktailItem(cocktail: snapshot.data?[index]);
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

class CocktailItem extends StatefulWidget {
  final Cocktail cocktail;
  const CocktailItem({Key? key, required this.cocktail}) : super(key: key);

  @override
  _CocktailItemState createState() => _CocktailItemState();
}

class _CocktailItemState extends State<CocktailItem> {
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
    return Card(
      child: ListTile(
        title: Text(widget.cocktail.strDrink),
        subtitle: Text(widget.cocktail.strCategory),
        leading: Image.network(widget.cocktail.strDrinkThumb),
        trailing: IconButton(
          onPressed: () {
            Navigator.of(context).push(
              MaterialPageRoute<void>(builder: (BuildContext context) {
                return CocktailCard(idDrink: widget.cocktail.idDrink);
              }),
            );
          },
          icon: const Icon(
            Icons.remove_red_eye,
          ),
        ),
      ),
    );
  }
}
