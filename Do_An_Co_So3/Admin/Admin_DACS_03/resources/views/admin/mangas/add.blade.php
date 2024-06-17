@extends('admin.main')

@section('contents')
    <form action="" method="POST">
        <div class="card-body">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Tên sách</label>
                        <input type="text" name="name" value="{{ old('name') }}" class="form-control"
                            id="exampleInputEmail1" placeholder="Nhập tên truyện">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Danh mục</label>

                        <select name="menu_id" id="menu_Add_Mangas" class="form-control">
                            <option value="0">Danh mục menu</option>
                            @foreach ($menus as $menu)
                                <option value="{{ $menu->id }}">{{ $menu->name }}</option>
                            @endforeach
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Tác giả</label>
                        <input type="text" name="author" value="{{ old('author') }}" class="form-control"
                            id="exampleInputEmail1" placeholder="Nhập tên tác giải">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Thể loại</label>
                        <select name="category_id" id="category_Add_Mangas" class="form-control">
                            <option value="0">Danh mục thể loại</option>
                        </select>
                    </div>

                </div>
            </div>
            <div class="form-group">
                <label for="exampleInputFile">Mô tả</label>
                <textarea name="description" class="form-control" id="description" cols="30" rows="5">{{ old('description') }}</textarea>

            </div>
            <div class="row">
                <div class="col-md-6 form-check">
                    <label for="exampleInputFile">Đề xuất</label>
                    <div class="form-group">
                        <div class="custom-control custom-radio">
                            <input class="custom-control-input" type="radio" value="1" id="suggest" name="suggest">
                            <label for="suggest" class="custom-control-label">Có</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input class="custom-control-input" type="radio" value="0" id="suggest" name="suggest"
                                checked="">
                            <label for="suggest" class="custom-control-label">Không</label>
                        </div>

                    </div>
                </div>
                <div class="col-md-6 form-check">
                    <label for="exampleInputFile">Tồn tại</label>
                    <div class="form-group">
                        <div class="custom-control custom-radio">
                            <input class="custom-control-input" type="radio" value="1" id="customRadio1"
                                name="active">
                            <label for="customRadio1" class="custom-control-label">Có</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input class="custom-control-input" type="radio" value="0" id="customRadio2"
                                name="active" checked="">
                            <label for="customRadio2" class="custom-control-label">Không</label>
                        </div>

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="image">Hình ảnh</label>
                <input type="file" name="file[]" class="form-control" id="upload">
                <div id="image_show" class="d-flex">

                </div>
                {{-- <input type="hidden" name="image" id="image"> --}}
            </div>

        </div>
        <!-- /.card-body -->

        <div class="card-footer bg-white">
            <button type="submit" class="btn btn-primary">Tạo sản phẩm</button>
        </div>
        @csrf
    </form>
    <!-- /.card -->
    </form>
@endsection

@section('footer')
    <script>
        ClassicEditor
            .create(document.querySelector('#description'))
            .then(editor => {
                console.log(editor);
            })
            .catch(error => {
                console.error(error);
            });
    </script>
@endsection
