@extends('admin.main')

@section('contents')
    <form action="" method="POST">
        <div class="card-body">
            <div class="form-group">
                <label for="exampleInputEmail1">Tên thể loại</label>
                <input type="name" class="form-control" name="name" id="exampleInputEmail1"
                    placeholder="Nhập tên thể loại">
            </div>
            <div class="form-group">
                <label for="image">Hình ảnh</label>
                <input type="file" name="file[]" class="form-control" id="upload">
                <div id="image_show" class="d-flex">

                </div>
                {{-- <input type="hidden" name="image" id="image"> --}}
            </div>
            <div class="form-group w-50">
                <label for="exampleInputPassword1">Thể loại</label>
                <select name="menu_id" id="menu_id" class="form-control">
                    <option value="0">Menu</option>
                    @foreach ($menus as $menu)
                        @if ($menu->name == "Truyện tranh" || $menu->name == "Tiểu thuyết")
                            <option value="{{ $menu->id }}">{{ $menu->name}}</option>
                        @endif
                    @endforeach
                </select>
            </div>
            <div class="form-check">
                <label for="exampleInputFile">Kích hoạt</label>
                <div class="form-group">
                    <div class="custom-control custom-radio">
                        <input class="custom-control-input" type="radio" value="1" id="customRadio1" name="active"
                            checked="">
                        <label for="customRadio1" class="custom-control-label">Có</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input class="custom-control-input" type="radio" value="0" id="customRadio2" name="active">
                        <label for="customRadio2" class="custom-control-label">Không</label>
                    </div>

                </div>
            </div>
        </div>
        <!-- /.card-body -->

        <div class="card-footer">
            <button type="submit" class="btn btn-primary">Thêm thể loại</button>
        </div>
        @csrf
    </form>
@endsection
