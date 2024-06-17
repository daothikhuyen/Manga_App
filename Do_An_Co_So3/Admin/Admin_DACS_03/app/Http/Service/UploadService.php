<?php

namespace  App\Http\Service;


class UploadService {

        public function store($request){
            if($request->hasFile('file')){

                try {
                    $uploadedFiles = $request->file('file');
                    $filePaths = [];

                    foreach ($uploadedFiles as $file) {
                        $name = $file->getClientOriginalName();
                        $pathFull = 'uploads/'.date("Y/m/d");
                        $path = $file->storeAs(
                                    'public/'.$pathFull,$name
                        );

                        $files = $pathFull .'/'. $name;
                        $filePaths[] = $files;
                    }

                    return $filePaths;

                } catch (\Throwable $th) {
                    return false;
                }



                // try {
                //     $name = $request->file('file')->getClientOriginalName();

                //     $pathFull = 'uploads/'.date("Y/m/d");

                //     $path = $request->file('file')->storeAs(
                //         'public/'.$pathFull,$name
                //     );

                //     return $pathFull .'/'. $name;

                // } catch (\Throwable $th) {
                //     return false;
                // }
            }
        }

}
