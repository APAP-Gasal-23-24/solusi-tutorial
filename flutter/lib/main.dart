import 'package:bacabaca_mobile/screens/login.dart';
import 'package:bacabaca_mobile/screens/view_all_buku.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Bacabaca',
      routes: {
        '/': (context) => const LoginFormScreen(),
        '/all-buku': (context) => const BukuScreen(),
      },
    );
  }
}
