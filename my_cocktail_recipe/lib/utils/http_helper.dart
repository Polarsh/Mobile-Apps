import 'dart:convert';
import 'dart:io';

import 'package:http/http.dart' as http;
import 'package:mycocktailrecipe/models/cocktail.dart';

class HttpHelper {
  Future<List> fetchCocktailsByName(String strDrink) async {
    String urlString =
        'https://www.thecocktaildb.com/api/json/v1/1/search.php?s=$strDrink';

    Uri url = Uri.parse(urlString);
    http.Response response = await http.get(url);

    if (response.statusCode == HttpStatus.ok) {
      final jsonResponse = json.decode(response.body);
      final maps = jsonResponse['drinks'];
      List cocktails = maps.map((map) => Cocktail.fromJson(map)).toList();
      return cocktails;
    }
    return [];
  }

  Future<List> fetchCocktailsById(String id) async {
    String urlString =
        'https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=$id';

    Uri url = Uri.parse(urlString);
    http.Response response = await http.get(url);

    if (response.statusCode == HttpStatus.ok) {
      final jsonResponse = json.decode(response.body);
      final maps = jsonResponse['drinks'];
      List cocktails = maps.map((map) => Cocktail.fromJson(map)).toList();
      return cocktails;
    }
    return [];
  }
}
