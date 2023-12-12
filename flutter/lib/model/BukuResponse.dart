class Buku {
  Buku({
    required this.uuid,
    required this.judul,
    required this.harga,
    required this.tahunTerbit
  });

  String uuid;
  String judul;
  double? harga;
  String? tahunTerbit;

  factory Buku.fromJson(Map<String, dynamic> json) => Buku(
    uuid: json["id"],
    judul: json["judul"],
    tahunTerbit: json["tahunTerbit"],
    harga: json["harga"],
  );
}
