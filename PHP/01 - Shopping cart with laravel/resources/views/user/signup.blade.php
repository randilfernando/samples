@extends('layouts.master')

@section('title')
    Sign Up
@endsection

@section('content')
    <div class="row mt-5">
        <div class="col-md-4 offset-md-4">
            <h1>Sign Up</h1>
            @if(count($errors) > 0)
                <div class="alert alert-danger">
                    @foreach($errors->all() as $error)
                        <p>{{ $error }}</p>
                    @endforeach
                </div>
            @endif
            <form action="{{ route('user.signup') }}" method="post">
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="Enter password">
                </div>
                <button type="submit" class="btn btn-primary">Signup</button>
                {{ csrf_field() }}
            </form>
        </div>
    </div>
@endsection