var gulp = require('gulp');
var sourceMaps = require('gulp-sourcemaps');
var concat = require('gulp-concat');
var clean = require('gulp-clean');
var browserify = require('browserify');
var sourceStream = require('vinyl-source-stream');
var buffer = require('vinyl-buffer');
var minify = require('gulp-minify');

var source = 'src/app/app.module.js';

var vendor = [
    'bower_components/angular/angular.js',
    'bower_components/angular-route/angular-route.js'
];

var buildPath = 'src/assets';

gulp.task('clean-vendor', function () {
  return gulp.src(buildPath + '/js/vendor.bundle.js', {read: false})
    .pipe(clean());
});

gulp.task('clean-source', function () {
  return gulp.src([buildPath + '/js/src.bundle.js', buildPath + '/js/src.bundle-min.js'], {read: false})
    .pipe(clean());
});

gulp.task('clean-scripts', ['clean-vendor', 'clean-source']);

gulp.task('vendor-scripts', function(){
    return gulp.src(vendor)
        .pipe(sourceMaps.init())
        .pipe(concat('vendor.bundle.js'))
        .pipe(sourceMaps.write())
        .pipe(gulp.dest(buildPath + '/js'));
});

gulp.task('source-scripts', function(){
    return browserify(source)
        .bundle()
        .pipe(sourceStream('src.bundle.js'))
        .pipe(buffer())
        .pipe(minify())
        .pipe(gulp.dest(buildPath + '/js'))
});

gulp.task('scripts', ['clean-scripts', 'vendor-scripts', 'source-scripts']);

gulp.task('default', ['scripts']);

gulp.task('watch', ['default'], function(){
    gulp.watch(source, ['clean-source', 'source-scripts']);
    gulp.watch('gulpfile.js', ['scripts']);
});
