@extends('layouts.master')

@section('title')
    Products
@endsection

@section('content')
    <div class="row mt-5">
        @foreach($products as $product)
            @include('partials.thumbnail', ['product' => $product])
        @endforeach
    </div>
@endsection