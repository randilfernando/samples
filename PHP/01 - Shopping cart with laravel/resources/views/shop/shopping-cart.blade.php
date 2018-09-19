@extends('layouts.master')

@section('title')
    Shopping Cart
@endsection

@section('content')
    @if(count($products) > 0)
        <div class="row mt-5">
            <div class="col-6 offset-3">
                <ul class="list-group">
                    @foreach($products as $product)
                        @include('partials.cart-item', ['product' => $product])
                    @endforeach
                </ul>
            </div>
        </div>

        <div class="row mt-5">
            <div class="col-6 offset-3">
                <h4 class="float-right"><b>Total: {{ $totalPrice }}$</b></h4>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-6 offset-3">
                <a class="btn btn-success" href="{{ route('product.checkout') }}">Checkout</a>
            </div>
        </div>
    @else
        <div class="row mt-5">
            <div class="col-6 offset-3">
                <h2>No Items in Cart</h2>
            </div>
        </div>
    @endif
@endsection