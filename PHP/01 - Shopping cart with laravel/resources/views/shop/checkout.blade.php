@extends('layouts.master')

@section('title')
    Checkout
@endsection

@section('content')
    <div class="row mt-5 mb-5">
        <div class="col-6 offset-3">
            <h1>Checkout</h1>
            <h4>Your total is: {{ $totalPrice }}$</h4>
            <form action="{{ route('product.checkout') }}" method="post" id="checkout-form">
                <div class="form-group">
                    <label for="card-name">Name on card</label>
                    <input type="text" class="form-control" id="card-name" name="card-name" required>
                </div>
                <div class="form-group">
                    <label for="card-number">Number</label>
                    <input type="text" class="form-control" id="card-number" name="card-number" required>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="card-year">Year</label>
                            <input type="text" class="form-control" id="card-year" name="card-year" required>
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="card-month">Month</label>
                            <input type="text" class="form-control" id="card-month" name="card-month" required>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="card-cvc">CVC</label>
                    <input type="text" class="form-control" id="card-cvc" name="card-cvc" required>
                </div>

                {{ csrf_field() }}

                <button type="submit" class="btn btn-success">Checkout</button>
            </form>
        </div>
    </div>
@endsection