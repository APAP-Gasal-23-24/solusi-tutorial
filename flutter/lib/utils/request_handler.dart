import 'dart:convert';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as http;

class RequestHandler {
  FlutterSecureStorage storage = const FlutterSecureStorage();
  Future<http.Response> fetchToken(String username, String password) async {
      var data = {"username" : username, "password": password};
      var response = await http.post(
        Uri.parse("http://127.0.0.1:9090/api/auth/login"),
        body: json.encode(data),
        headers:{
          "Content-type": "application/json"
        }
      );
      return response;
    }
}