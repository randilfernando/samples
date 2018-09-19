@extends('layouts.master')

@section('title')
    Checkout
@endsection

@section('scripts')
    <script type="text/javascript" src="https://js.stripe.com/v2/"></script>
    <script type="text/javascript">
        Stripe.setPublishableKey('pk_test_6EZwVGVbs8W1nT6KSfurin7D');

        Stripe.card.createToken({
            number: $('.card-number').val(),
            cvc: $('.card-cvc').val(),
            exp_month: $('.card-month').val(),
            exp_year: $('.card-year').val()
        }, stripeResponseHandler);

        function stripeResponseHandler(status, response) {
            // Grab the form:
            const $form = $('#payment-form');

            if (response.error) {
                // Show the errors on the form
                $form.find('.payment-errors').text(response.error.message);
                $form.find('button').prop('disabled', false); // Re-enable submission
            } else {
                // Get the token ID:
                const token = response.id;
                // Insert the token into the form so it gets submitted to the server:
                $form.append($('<input type="hidden" name="stripeToken" />').val(token));
                // Submit the form:
                $form.get(0).submit();
            }
        }
    </script>
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