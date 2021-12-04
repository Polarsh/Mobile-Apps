class Cocktail {
  String idDrink;
  String strDrink;
  String strCategory;
  String strInstructions;
  String strDrinkThumb;

  Cocktail(
      {required this.idDrink,
      required this.strDrink,
      required this.strCategory,
      required this.strInstructions,
      required this.strDrinkThumb});

  factory Cocktail.fromJson(Map<String, dynamic> json) {
    return Cocktail(
        idDrink: json['idDrink'],
        strDrink: json['strDrink'],
        strCategory: json['strCategory'],
        strInstructions: json['strInstructions'],
        strDrinkThumb: json['strDrinkThumb']);
  }

  Map<String, dynamic> toMap() {
    return {
      'idDrink': idDrink,
      'strDrink': strDrink,
      'strCategory': strCategory,
      'strInstructions': strInstructions,
      'strDrinkThumb': strDrinkThumb,
    };
  }
}
