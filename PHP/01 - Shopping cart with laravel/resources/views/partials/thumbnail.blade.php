<div class="col-lg-3 col-md-4 col-sm-6 mb-5">
    <div class="card">
        <img class="card-img-top"
             src="{{ $product->imagePath }}"
             alt="Card image cap">
        <div class="card-body">
            <h5 class="card-title">{{ $product->title }}</h5>
            <p class="card-text text-secondary">
                {{ $product->description }}
            </p>
            <div class="clearfix">
                <span class="float-left price">{{ $product->price }}$</span>
                <a href="{{ route('product.addToCart', ['id' => $product->id]) }}" class="btn btn-success float-right">Add to cart</a>
            </div>
        </div>
    </div>
</div>