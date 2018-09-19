<li class="list-group-item">
    <h5>
        <b>{{ $product['item']->title }}</b>
        <span class="badge badge-secondary">{{ $product['price'] }}$</span>
    </h5>
    <h2><span class="badge badge-primary float-right">{{ $product['qty'] }}</span></h2>

    <div class="btn-group" role="group" aria-label="Cart buttons">
        <div class="dropdown">
            <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Action
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="#">Drop</a>
                <a class="dropdown-item" href="#">Drop All</a>
            </div>
        </div>
    </div>
</li>