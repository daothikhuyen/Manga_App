@extends('admin.main')

@section('contents')
    <form action="" method="POST">
        <div class="card-body">
            <div class="form-group">
                <input type="hidden" name="manga_id" value="{{$manga->id}}" class="form-control" id="exampleInputEmail1"
                    placeholder="Nhập tên truyện">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Tên chương/chapter</label>
                <input type="text" name="title" value="{{ old('name') }}" class="form-control"
                    id="exampleInputEmail1" placeholder="Nhập tiêu đề">
            </div>
            @if ($manga->menu->name == "Tiểu thuyết")
                <div class="form-group">
                    <label for="exampleInputFile">Nội dung truyện </label>
                    <textarea name="content" class="form-control" id="description" cols="30" rows="30">{{ old('description') }}</textarea>

                </div>
                @elseif ($manga->menu->name == "Truyện tranh")
                <div class="form-group">
                    <label for="image">Hình ảnh</label>
                    <input type="file" name="file[]" class="form-control" id="upload" multiple>
                    <div id="image_show" class="d-flex">

                    </div>
                </div>
            @endif

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
