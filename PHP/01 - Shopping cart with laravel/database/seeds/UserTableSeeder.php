<?php

use Illuminate\Database\Seeder;
use \App\User;

class UserTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $user = new User([
           'email' => 'randil.fernando.rf@gmail.com',
           'password' => bcrypt('1234')
        ]);
        $user->save();
    }
}
