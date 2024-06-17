@extends('admin.main')

@section('contents')
    <form action="" method="POST">
        <div class="card-body">
            @foreach ($chapters as $chapter)
            @endforeach
            <div class="form-group">
                <input type="hidden" name="chapter_id" value="{{ $chapter->id }}" class="form-control"
                    id="exampleInputEmail1"placeholder="Nhập tên truyện">
                @foreach ($chapter->link as $linkItem)
                    <input type="hidden" name="link_id[]" value="{{ $linkItem->id }}" class="form-control"
                        id="exampleInputEmail1"placeholder="Nhập tên truyện">
                @endforeach
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Tên chương/chapter</label>
                <input type="text" name="title" value="{{ $chapter->title }}" class="form-control"
                    id="exampleInputEmail1" placeholder="Nhập tiêu đề">
            </div>
            <div class="form-group">
                @foreach ($chapter->link as $key => $linkItem)
                    @if ($linkItem->content != null)
                        <div class="form-group ">
                            <label for="exampleInputFile">Nội dung truyện </label>
                            <textarea name="content" class="form-control" id="description" cols="30" rows="30">{{ $linkItem->content }}</textarea>
                        @else
                            @if ($key == 0)
                                <label for="image">Hình ảnh</label>
                                <input type="file" name="file[]" class="form-control" id="upload" multiple>
                                <div class="form-group d-flex">
                                    <div id="image_show" class="d-flex">
                            @endif
                            <a href="/storage/{{ $linkItem->image }}">
                                <img src="/storage/{{ $linkItem->image }}" alt="" style="width:50px;"
                                    class="mx-1 border-1">
                                <input type="hidden" name="image[]" id="image" value = "{{ $linkItem->image }}">
                            </a>
                    @endif
                @endforeach
            </div>
        </div>
        </div>

        <div class="card-footer bg-white">
            <button type="submit" class="btn btn-primary">Cập nhật</button>
        </div>
        @csrf
    </form>
    <!-- /.card -->
    </form>
@endsection

@section('footer')
@endsection
