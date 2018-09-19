<?php

use Illuminate\Database\Seeder;

class ProductTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $product = new \App\Product([
           'imagePath' => 'https://images-na.ssl-images-amazon.com/images/I/51HSkTKlauL._SX346_BO1,204,203,200_.jpg',
           'title' => 'Harry Potter',
           'description' => 'Super cool at least as a child.',
           'price' => 10
        ]);

        $product->save();

        $product = new \App\Product([
            'imagePath' => 'https://vignette.wikia.nocookie.net/lotr/images/8/87/Ringstrilogyposter.jpg/revision/latest?cb=20070806215413',
            'title' => 'Lord of the rings',
            'description' => 'Super cool at least as a child.',
            'price' => 10
        ]);

        $product->save();

        $product = new \App\Product([
            'imagePath' => 'https://icdn3.digitaltrends.com/image/skyrim-11-1200x630-c-ar1.91.jpg',
            'title' => 'Skyrim',
            'description' => 'Super cool at least as a child.',
            'price' => 20
        ]);

        $product->save();

        $product = new \App\Product([
            'imagePath' => 'https://static1.squarespace.com/static/51b3dc8ee4b051b96ceb10de/t/5a95be278165f56deea7396d/1519762988249/',
            'title' => 'Witcher',
            'description' => 'Super cool at least as a child.',
            'price' => 20
        ]);

        $product->save();
    }
}
